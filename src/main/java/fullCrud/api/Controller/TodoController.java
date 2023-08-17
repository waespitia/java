package fullCrud.api.Controller;

import fullCrud.api.Exeptions.resourceNotFoundException;
import fullCrud.api.Model.Empleado;
import fullCrud.api.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

    @Autowired
    private TodoRepository respositorio;

    //este metodo trae todos los elementos de la lista
    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosEmpleados(){

        return respositorio.findAll();
    }

    //este metodo se utiliza para guardar al empleado
    @PostMapping(value = "/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return respositorio.save(empleado);
    }

    //Este metodo sirve para buscar empleado por id
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = respositorio.findById(id)
                .orElseThrow(() -> new resourceNotFoundException("No existe el empleado con el ID : " + id));

        return ResponseEntity.ok(empleado);

    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable long id,@RequestBody Empleado detallesEmpleado){

        Empleado empleado = respositorio.findById(id).get();

        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado = respositorio.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id) {

        Empleado empleado = respositorio.findById(id).get();

        respositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
