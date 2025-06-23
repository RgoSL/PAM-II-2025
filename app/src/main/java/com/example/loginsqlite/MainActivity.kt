import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginsqlite.AppDatabase
import com.example.loginsqlite.screens.Listar
import com.example.loginsqlite.screens.Registrar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(this)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "cadastro") {
                composable("cadastro") {
                    Registrar(navController, db.regDao())
                }
                composable("listar") {
                    Listar(navController, db.regDao())
                }
            }
        }
    }
}
