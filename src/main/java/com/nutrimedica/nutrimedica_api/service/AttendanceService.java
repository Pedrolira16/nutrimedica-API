package com.nutrimedica.nutrimedica_api.service;

import com.nutrimedica.nutrimedica_api.dto.Attendance;
import com.nutrimedica.nutrimedica_api.dto.User;
import com.nutrimedica.nutrimedica_api.dto.Patient;
import com.nutrimedica.nutrimedica_api.dto.Place;
import com.nutrimedica.nutrimedica_api.repository.AttendanceRepository;
import com.nutrimedica.nutrimedica_api.repository.UserRepository;
import com.nutrimedica.nutrimedica_api.repository.PatientRepository;
import com.nutrimedica.nutrimedica_api.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public AttendanceService(
        AttendanceRepository attendanceRepository,
        UserRepository userRepository,
        PatientRepository patientRepository,
        PlaceRepository placeRepository) {
        super();
		{
		this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.placeRepository = placeRepository;
    	}
	}

    public void createAttendance(Attendance attendance) {
        User user = userRepository.findById(attendance.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        Patient patient = patientRepository.findById(attendance.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        Place place = placeRepository.findById(attendance.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("Local não encontrado."));

        attendanceRepository.createAttendance(attendance);
    }

    public List<Attendance> getAttendances() {
        return attendanceRepository.getAttendances();
    }

    public void deleteAttendance(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Atendimento não encontrado.");
        }
        attendanceRepository.deleteAttendance(id);
    }

    public void updateAttendance(Long id, Attendance attendance) {
        attendance.setId(id);
        attendanceRepository.updateAttendance(attendance);
    }

	public List<Attendance> listById(Long id) {
		return attendanceRepository.listById(id);
	}
}
