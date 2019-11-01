import * as React from "react"
import { Image, ImageStyle, Platform, TextStyle, View, ViewStyle, NativeModules } from "react-native"
import { NavigationScreenProps } from "react-navigation"
import { Screen } from "../../components/screen"
import { Text } from "../../components/text"
import { Button } from "../../components/button"
import { Wallpaper } from "../../components/wallpaper"
import { Header } from "../../components/header"
import { color, spacing } from "../../theme"
import { logoIgnite, heart } from "./"
import { BulletItem } from "../../components/bullet-item"
// import { Api } from "../../services/api"
// import { save } from "../../utils/storage"

//MercadoPago
const MPExample = NativeModules.MercadoPagoTest;

console.log("MPExample ", MPExample)

const FULL: ViewStyle = { flex: 1 }
const CONTAINER: ViewStyle = {
  backgroundColor: color.transparent,
  paddingHorizontal: spacing[4],
}
const DEMO: ViewStyle = {
  paddingVertical: spacing[4],
  paddingHorizontal: spacing[4],
  backgroundColor: "#5D2555",
}
const BOLD: TextStyle = { fontWeight: "bold" }
const DEMO_TEXT: TextStyle = {
  ...BOLD,
  fontSize: 13,
  letterSpacing: 2,
}
const HEADER: TextStyle = {
  paddingTop: spacing[3],
  paddingBottom: spacing[5] - 1,
  paddingHorizontal: 0,
}
const HEADER_TITLE: TextStyle = {
  ...BOLD,
  fontSize: 12,
  lineHeight: 15,
  textAlign: "center",
  letterSpacing: 1.5,
}
const TITLE: TextStyle = {
  ...BOLD,
  fontSize: 28,
  lineHeight: 38,
  textAlign: "center",
  marginBottom: spacing[5],
}
const TAGLINE: TextStyle = {
  color: "#BAB6C8",
  fontSize: 15,
  lineHeight: 22,
  marginBottom: spacing[4] + spacing[1],
}
const IGNITE: ImageStyle = {
  marginVertical: spacing[6],
  alignSelf: "center",
}
const LOVE_WRAPPER: ViewStyle = {
  flexDirection: "row",
  alignItems: "center",
  alignSelf: "center",
}
const LOVE: TextStyle = {
  color: "#BAB6C8",
  fontSize: 15,
  lineHeight: 22,
}
const HEART: ImageStyle = {
  marginHorizontal: spacing[2],
  width: 10,
  height: 10,
  resizeMode: "contain",
}
const HINT: TextStyle = {
  color: "#BAB6C8",
  fontSize: 12,
  lineHeight: 15,
  marginVertical: spacing[2],
}

export interface DemoScreenProps extends NavigationScreenProps<{}> {}

export const DemoScreen: React.FunctionComponent<DemoScreenProps> = props => {
  const goBack = React.useMemo(() => () => props.navigation.goBack(null), [props.navigation])

  const cardInfo = {
    cardNumber: "4509953566233704",
    expMonth: 12,
    expYear: 2020,
    secCode: "123",
    cardHName: "APRO",
    cardHIType: "DNI",
    cardHIdent: "12345678"
  }

  // La info de la tarjeta guardada va a venir de nuestro backend
  const customercardInfo = {
    cardId: "1569506780900",
    secCode: "123"
  }

  // Para tarjetas que ya se encuentran guardadas en un customer, solo se necesita el Id de la tarjeta y el cod de seguridad
  async function getCustomerCardToken() {
    try {
      var cardToken = await MPExample.getCustomerCardToken(customercardInfo);
      console.log("customerCardToken ", cardToken)
    } catch(e) {
      // TODO 
      // Pantalla de error con la exception devuelta
      console.error(e)
      console.log(e)
    }
  }

  // Este metodo se utlizara solamente cuadno ingresa una tarjeta de credito nueva
  async function getCardToken() {
    try {
      var cardToken = await MPExample.getCardToken(cardInfo);
      console.log("cardToken ", cardToken)
      // TODO 
      // Mandar al back el mail y el token de tarjeta para crear customer y su respectiva tarjeta. Tambien guardar datos en la base 
    } catch(e) {
      // TODO 
      // Pantalla de error con la exception devuelta
      console.error(e)
      console.log(e)
    }

    // return fetch('https://facebook.github.io/react-native/movies.json')
    //   .then((response) => response.json())
    //   .then((responseJson) => {
    //     return responseJson.movies;
    //   })
    //   .catch((error) => {
    //     console.error(error);
    //   });
  }

  return (
    <View style={FULL}>
      <Wallpaper />
      <Screen style={CONTAINER} preset="scroll" backgroundColor={color.transparent}>
        <Header
          headerTx="demoScreen.howTo"
          leftIcon="back"
          onLeftPress={goBack}
          style={HEADER}
          titleStyle={HEADER_TITLE}
        />
        <Text style={TITLE} preset="header" tx="demoScreen.title" />
        <Text style={TAGLINE} tx="demoScreen.tagLine" />
        <BulletItem text="Load up Reactotron!  You can inspect your app, view the events, interact, and so much more!" />
        <BulletItem text="Integrated here, Navigation with State, TypeScript, Storybook, Solidarity, and i18n." />
        <View>
          <Button
            style={DEMO}
            textStyle={DEMO_TEXT}
            tx="demoScreen.getCardToken"
            onPress={getCardToken}
          />
          <Button
            style={DEMO}
            textStyle={DEMO_TEXT}
            tx="demoScreen.getCustomerCardToken"
            onPress={getCustomerCardToken}
          />
          <Text style={HINT} tx={`demoScreen.${Platform.OS}ReactotronHint`} />
        </View>
        <Image source={logoIgnite} style={IGNITE} />
        <View style={LOVE_WRAPPER}>
          <Text style={LOVE} text="Made with" />
          <Image source={heart} style={HEART} />
          <Text style={LOVE} text="by Infinite Red" />
        </View>
      </Screen>
    </View>
  )
}
