import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import model.CharacterDb
import org.w3c.dom.CharacterData

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent = Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun CharacterData.convertToCharacterDb(character : CharacterDb) {

}