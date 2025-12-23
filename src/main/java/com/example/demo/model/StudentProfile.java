@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OPTIONAL – tests don't depend on this
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    // 🔴 REQUIRED BY TESTS
    private Long studentId;

    // 🔴 REQUIRED BY TESTS
    private String email;

    private String fullName;
    private Integer age;
    private String course;
    private Integer yearOfStudy;
    private String gender;

    private String roomTypePreference;

    private LocalTime sleepTime;
    private LocalTime wakeTime;

    private Boolean smoking;
    private Boolean drinking;

    private String noiseTolerance;
    private String studyTime;

    private String department;
    private Integer yearLevel;

    private Boolean active = true;
}
