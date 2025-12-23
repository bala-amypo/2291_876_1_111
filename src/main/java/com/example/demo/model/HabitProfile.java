@Entity
@Table(name = "habit_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProfile {

    // 🔽 REQUIRED BY TESTS
    public enum CleanlinessLevel { LOW, MEDIUM, HIGH }
    public enum NoiseTolerance { LOW, MEDIUM, HIGH }
    public enum SleepSchedule { EARLY_SLEEPER, NIGHT_OWL, FLEXIBLE }
    public enum SocialPreference { INTROVERT, AMBIVERT, EXTROVERT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private StudentProfile student;

    private Boolean smoking = false;
    private Boolean drinking = false;

    private LocalTime sleepTime;
    private LocalTime wakeTime;

    // 👇 Leave these as String (no need to change)
    private String cleanlinessLevel;
    private String noisePreference;
    private String studyStyle;
    private String socialPreference;
    private String visitorsFrequency;
    private String sleepSchedule;
    private Integer studyHoursPerDay = 0;
    private String noiseTolerance;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
