import androidx.appcompat.app.AppCompatActivity
import com.example.datingapp.R

data class Profile(val imageResId: Int, val name: String)

// ...

class MainActivity : AppCompatActivity() {
    // ...

    private fun getProfiles(): List<Profile> {
        val profiles = mutableListOf<Profile>()
        profiles.add(Profile(R.drawable.ic_blank_profile_picture_973460, "Name1"))
        profiles.add(Profile(R.drawable.ic_baseline_arrow_drop_down_24, "Name2"))
        // Add more profiles as needed

        return profiles
    }

    // ...
}