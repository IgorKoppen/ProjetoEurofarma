package br.com.connectfy.EurofarmaCliente.dtos.training;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeTrainingInfoDTO;
import br.com.connectfy.EurofarmaCliente.dtos.instructor.InstructorDetailsDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<InstructorDetailsDTO> instructors;
    private List<EmployeeTrainingInfoDTO> attendanceList;

    // Getters and Setters

    public List<InstructorDetailsDTO> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorDetailsDTO> instructors) {
        this.instructors = instructors;
    }

    public List<EmployeeTrainingInfoDTO> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<EmployeeTrainingInfoDTO> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
