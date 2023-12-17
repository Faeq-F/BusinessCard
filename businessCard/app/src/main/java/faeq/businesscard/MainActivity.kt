package faeq.businesscard

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    stringResource(getPhoneNumber()),
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
    val jobTitleFontFamily = FontFamily(
      Font(
        googleFont = GoogleFont("Baloo Bhaijaan 2"),
        weight = FontWeight.W500,
        fontProvider = provider
      )
    )
    Image(
    contentDescription="Fyke Profile Image",
      painter = painterResource(id = R.drawable.transparent_profile_pic),
      modifier = Modifier
        .height(180.dp)
        .width(150.dp)
    )
    Text(
      text = name,
      fontSize = 50.sp,
      fontFamily = nameFontFamily,
      modifier = modifier.padding(top=16.dp)
    )
    Text(
      text = title,
      fontSize = 17.sp,
      fontFamily = jobTitleFontFamily,
      modifier = modifier
    )
  }
}


fun getPhoneNumber(): Int {
  return R.string.phone_number
}

@Composable
fun ExtraDetailsSection(pronunciation:String, number:String, email:String, modifier : Modifier = Modifier){
  val detailsFontFamily = FontFamily(
    Font(
      googleFont = GoogleFont("Urbanist"),
      weight = FontWeight.W300,
      fontProvider = provider
    )
  )
  
Column(modifier = Modifier.absoluteOffset(0.dp,70.dp)){
  Row (modifier = Modifier.padding(bottom=10.dp)) {
    Icon(
      Icons.Rounded.Person,
      contentDescription = "Pronunciation Icon"
    )
    Text(
      text = pronunciation,
      fontFamily = detailsFontFamily,
      modifier = Modifier.padding(start=15.dp)
    )
  }
  Row (modifier = Modifier.padding(bottom=10.dp)){
    Icon(
      Icons.Rounded.Phone,
      contentDescription = "Phone number Icon"
    )
    Text(text = number,fontFamily = detailsFontFamily,modifier = Modifier.padding(start=15.dp))
  }
  Row{
    Icon(Icons.Rounded.Email, contentDescription = "Email Icon")
    Text(text = email,fontFamily = detailsFontFamily,modifier = Modifier.padding(start=15.dp))
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
    Column (verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment
      .CenterHorizontally) {
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