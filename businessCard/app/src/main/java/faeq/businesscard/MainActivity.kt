package faeq.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import faeq.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
  
  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BusinessCardTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          View()
        }
      }
    }
  }
}

val provider = GoogleFont.Provider(
  providerAuthority = "com.google.android.gms.fonts",
  providerPackage = "com.google.android.gms",
  certificates = R.array.com_google_android_gms_fonts_certs
)

@Composable
fun View(){
  BusinessCard(
    stringResource(R.string.name),
    stringResource(R.string.job_title),
    stringResource(R.string.name_pronunciation),
    stringResource(R.string.phone_number),
    stringResource(R.string.email),
    Modifier.fillMaxSize()
  )
}

@Composable
fun NameSection(name : String, title:String, modifier : Modifier = Modifier){
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    val nameFontFamily = FontFamily(
      Font(
        googleFont = GoogleFont("Foldit"),
        weight = FontWeight.W300,
        style = FontStyle.Normal,
        fontProvider = provider
      )
    )
    Image(
    contentDescription="Android logo",
      painter = painterResource(id = R.drawable.ic_launcher_foreground),
      modifier = Modifier.height(100.dp),
      contentScale = ContentScale.FillBounds
    )
    Text(
      text = name,
      fontSize = 50.sp,
      fontFamily = nameFontFamily,
      modifier = modifier
    )
    Text(
      text = title,
      fontSize = 17.sp,
      modifier = modifier
    )
  }
}

@Composable
fun ExtraDetailsSection(pronunciation:String, number:String, email:String, modifier : Modifier = Modifier){
Column(verticalArrangement = Arrangement.Bottom){
  Row (modifier = Modifier.padding(bottom=10.dp)) {
    Icon(
      Icons.Rounded.Person,
      contentDescription = "Pronunciation Icon"
    )
    Text(
      text = pronunciation,
      modifier = Modifier.padding(start=15.dp)
    )
  }
  Row (modifier = Modifier.padding(bottom=10.dp)){
    Icon(
      Icons.Rounded.Phone,
      contentDescription = "Phone number Icon"
    )
    Text(text = number,modifier = Modifier.padding(start=15.dp))
  }
  Row{
    Icon(Icons.Rounded.Email, contentDescription = "Email Icon")
    Text(text = email,modifier = Modifier.padding(start=15.dp))
  }
}
}

@Composable
fun BusinessCard(name : String, title:String, pronunciation:String, number:String, email:String,
                 modifier : Modifier = Modifier) {
  Surface(
    color = Color.hsl(270F,0.8F,0.85F),
    modifier = modifier
  ){
    Column (verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
      NameSection(name, title)
      ExtraDetailsSection(pronunciation, number, email)
    }
  }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_7_pro")
@Composable
fun GreetingPreview() {
  BusinessCardTheme {
    View()
  }
}