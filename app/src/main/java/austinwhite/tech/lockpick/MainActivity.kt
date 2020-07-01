package austinwhite.tech.lockpick

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start: Button = findViewById(R.id.startbutton)
        val newGame: Button = findViewById(R.id.newgame)

        val pickOne: SeekBar = findViewById(R.id.seek1)
        val pickTwo: SeekBar = findViewById(R.id.seek2)
        val pickThree: SeekBar = findViewById(R.id.seek3)

        pickOne.isEnabled = false
        pickTwo.isEnabled = false
        pickThree.isEnabled = false

        val time: TextView = findViewById(R.id.timer)

        val combo1: TextView = findViewById(R.id.combo1)
        val combo2: TextView = findViewById(R.id.combo2)
        val combo3: TextView = findViewById(R.id.combo3)

        val pickdebug1: TextView = findViewById(R.id.debugpick1)
        val pickdebug2: TextView = findViewById(R.id.debugpick2)
        val pickdebug3: TextView = findViewById(R.id.debugpick3)

        val randnumber1 = ThreadLocalRandom.current().nextInt(100)
        val randnumber2 = ThreadLocalRandom.current().nextInt(100)
        val randnumber3 = ThreadLocalRandom.current().nextInt(100)

        newGame.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        start.setOnClickListener {
            start.isEnabled = false

            combo1.text = randnumber1.toString()
            combo2.text = randnumber2.toString()
            combo3.text = randnumber3.toString()

            pickOne.isEnabled = true
            pickTwo.isEnabled = true
            pickThree.isEnabled = true

            val countDown = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    time.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    time.text = getString(R.string.youLose)
                    pickOne.isEnabled = false
                    pickTwo.isEnabled = false
                    pickThree.isEnabled = false

                }
            }.start()

            pickOne.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    //val number = progress.toString()
                    //pickdebug1.text = number
                    //for debug purposes
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    pickOne.isEnabled = false

                    if (randnumber1 == pickOne.progress) {
                        pickdebug1.text = getString(R.string.correct)
                    } else {
                        countDown.cancel()

                        time.text = getString(R.string.youLose)
                        pickOne.isEnabled = false
                        pickTwo.isEnabled = false
                        pickThree.isEnabled = false
                    }

                }
            })
            pickTwo.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    //val number = progress.toString()
                    //pickdebug2.text = number
                    //for debug purposes
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    pickTwo.isEnabled = false

                    if (randnumber2 == pickTwo.progress) {
                        pickdebug2.text = getString(R.string.correct)
                    } else {
                        countDown.cancel()

                        time.text = getString(R.string.youLose)
                        pickOne.isEnabled = false
                        pickTwo.isEnabled = false
                        pickThree.isEnabled = false
                    }

                }
            })

            pickThree.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    //val number = progress.toString()
                    //pickdebug3.text = number
                    //for debug purposes
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    pickThree.isEnabled = false

                    if (randnumber1 == pickOne.progress) {
                        countDown.cancel()
                        pickdebug3.text = getString(R.string.correct)
                        time.text = getString(R.string.winner)
                    } else {
                        countDown.cancel()

                        time.text = getString(R.string.youLose)
                        pickOne.isEnabled = false
                        pickTwo.isEnabled = false
                        pickThree.isEnabled = false
                    }

                }
            })

        }

    }
}