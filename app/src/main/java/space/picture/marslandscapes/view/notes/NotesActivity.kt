package space.picture.marslandscapes.view.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import space.picture.marslandscapes.R
import space.picture.marslandscapes.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.notes_container, NotesFragment.newInstance()).commit()
    }
}
