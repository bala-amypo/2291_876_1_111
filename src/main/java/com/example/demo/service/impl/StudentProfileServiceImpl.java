@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final UserAccountRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     UserAccountRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile findByStudentId(Long studentId) {
        return studentRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile profile = getStudentById(id);
        profile.setActive(active);
        return studentRepo.save(profile);
    }
}
