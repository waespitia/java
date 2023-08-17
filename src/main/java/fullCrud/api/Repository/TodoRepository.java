package fullCrud.api.Repository;

import fullCrud.api.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository <Empleado, Long> {


}
