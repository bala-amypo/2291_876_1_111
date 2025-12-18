import java.time.LocalDateTime;
import com.example.demo.model.enums.SleepSchedule;
import com.example.demo.model.enums.CleanlinessLevel;
import com.example.demo.model.enums.NoiseTolerance;
import com.example.demo.model.enums.SocialPreference;
import jakarta.persistence.*;

@Entity
@Table(name = "habit_profiles")
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    private Integer studyHoursPerDay;

    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public SleepSchedule getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(SleepSchedule sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public CleanlinessLevel getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(CleanlinessLevel cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public NoiseTolerance getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(NoiseTolerance noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public SocialPreference getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(SocialPreference socialPreference) {
        this.socialPreference = socialPreference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public HabitProfile(Long id, Long studentId, SleepSchedule sleepSchedule, Integer studyHoursPerDay,
            CleanlinessLevel cleanlinessLevel, NoiseTolerance noiseTolerance, SocialPreference socialPreference,
            LocalDateTime updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.sleepSchedule = sleepSchedule;
        this.studyHoursPerDay = studyHoursPerDay;
        this.cleanlinessLevel = cleanlinessLevel;
        this.noiseTolerance = noiseTolerance;
        this.socialPreference = socialPreference;
        this.updatedAt = updatedAt;
    }

    public HabitProfile() {
    }
    
    
}