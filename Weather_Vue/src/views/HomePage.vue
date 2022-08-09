<template>
  <ion-page>
    <ion-header>
      <ion-toolbar color="primary">
        <ion-title>Ma Météo</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <div class="ion-text-center milieu">
        <p>{{ dateStr }}</p>
      </div>
      <ion-item>
        <ion-label>Ville</ion-label>

        <!--這個v-model的selectValue就是下面ion-select-option的value，自動關聯上了
        ,但是變量名可以隨便取，如果data中不配置，不能設初始值，也會報警告-->
        <ion-select
          @ionChange="getOption(selectValue)"
          v-model="selectValue"
          placeholder="Choisir"
        >
          <ion-select-option value="Laval,ca">Laval</ion-select-option>
          <ion-select-option value="Montreal,ca">Montreal</ion-select-option>
          <ion-select-option value="Quebec,ca">Quebec</ion-select-option>
          <ion-select-option value="London,uk">London</ion-select-option>
          <ion-select-option value="Beijing,cn">Beijing</ion-select-option>
          <ion-select-option value="Pa">Position actuelle</ion-select-option>
        </ion-select>
      </ion-item>

      <!--

<ion-row>
          <ion-col>
         
          </ion-col>
          <ion-col>
          </ion-col>
          <ion-col>
          </ion-col>
          <ion-col>
          </ion-col>
        </ion-row>

      -->
      <div class="ion-text-center milieu">
        <p>{{ this.cityName }}</p>

        <p>{{ this.temp }}°C</p>
        <ion-row>
          <ion-col> </ion-col>
          <ion-col>
            <!--ion-item class="ion-align-items-center"-->
              <ion-img :src="image"></ion-img>
            <!--/ion-item-->
          </ion-col>
          <ion-col> </ion-col>
        </ion-row>
        <p>{{ this.weather }}</p>
      </div>
    </ion-content>

    <ion-footer>
      <ion-toolbar color="primary">
        <ion-title>CHANG,Liang</ion-title>
      </ion-toolbar>
    </ion-footer>
  </ion-page>
</template>

 <script lang="ts">
import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonLabel,
  IonItem,
  IonSelect,
  IonSelectOption,
  IonImg,
} from "@ionic/vue";
import { alertController } from "@ionic/vue";
import { defineComponent } from "vue";
import { Test } from "../fun/functions";
import { Geolocation } from "@capacitor/geolocation";
import { reactive } from "vue";

export default defineComponent({
  components: {
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
    IonLabel,
    IonItem,
    IonSelect,
    IonSelectOption,
    IonImg,
  },

  setup() {
    var imageName = "01d";

    const api_key = "486369f7fd75ed0b2103fef6226f0f14";

    const url =
      "http://api.openweathermap.org/data/2.5/weather?q=Montreal,ca&mode=json&units=metric&appid=" +
      api_key;

    // fetch(url)
    //   .then((reponse) => reponse.json())
    //   .then((data) => {
    //     imageName = data.weather[0].icon;
    //   });

    return {
      imageName,
    };
  },

  methods: {
    ionViewDidEnter() {
      console.log("Meteo home page  entered");
      const api_key = "486369f7fd75ed0b2103fef6226f0f14";

      const url =
        "http://api.openweathermap.org/data/2.5/weather?q=Montreal,ca&mode=json&units=metric&appid=" +
        api_key;

      fetch(url)
        .then((reponse) => reponse.json())
        .then((data) => {
          this.imageName = data.weather[0].icon;

          console.log(
            "Meteo home page  entered this.imageName:" + this.imageName
          );
          this.weather = data.weather[0].main;
          this.image =
            "https://openweathermap.org/img/wn/" + this.imageName + "@2x.png";
          this.cityName = "Montreal";
          this.temp = data.main.temp;
        });
    },

    ionViewWillEnter() {
      console.log("Meteo home page will enter");
      const api_key = "486369f7fd75ed0b2103fef6226f0f14";

      const url =
        "http://api.openweathermap.org/data/2.5/weather?q=Montreal,ca&mode=json&units=metric&appid=" +
        api_key;

      fetch(url)
        .then((reponse) => reponse.json())
        .then((data) => {
          // console.log("1111111111111" + this.weather);
          this.imageName = data.weather[0].icon;
          // console.log("22222222222222:" + this.imageName);
          console.log("22222222222222:" + this.image);
          this.weather = data.weather[0].main;
          // console.log("33333333333333333:" + this.cityName);
          this.cityName = "Montreal";
          this.temp = data.main.temp;
          // console.log("weather:" + this.weather);
          // console.log("imageName:" + this.imageName);
          // console.log("cityName:" + this.cityName);
        });
    },
    getOption(cityName: string) {
      const test = new Test();
      const api_key = "e6a50b72a95ff80296f53b675315eea3";

      if (cityName == "Pa") {
        //选当前位置

        test.getCurrentPosition().then((city) => {
          this.latitude = city.getLatitude();
          this.longitude = city.getLongitude();

          //const api_key = "e6a50b72a95ff80296f53b675315eea3";
          const url =
            "https://api.openweathermap.org/data/2.5/weather?lat=" +
            this.latitude +
            "&lon=" +
            this.longitude +
            "&units=metric&appid=" +
            api_key +
            "";
          fetch(url)
            .then((reponse) => reponse.json())
            .then((data) => {
              console.log(data);

              this.weather = data.weather[0].main;
              this.imageName = data.weather[0].icon;
              this.cityName = data.name;
              this.image =
                "https://openweathermap.org/img/wn/" +
                this.imageName +
                "@2x.png";

              this.temp = data.main.temp;
            });
        });
      } else {
        //按城市请求
        console.log("cityName:" + cityName);
        //const meteo  = await  test.loadMeteoByName(cityName)
        const url =
          "http://api.openweathermap.org/data/2.5/weather?q=" +
          cityName +
          "&mode=json&units=metric&appid=" +
          api_key;
        //console.log("当前请求:"+url);
        fetch(url)
          .then((reponse) => reponse.json())
          .then((data) => {
            this.weather = data.weather[0].main;
            this.imageName = data.weather[0].icon;
            this.cityName = cityName.split(",")[0];
            this.image =
              "https://openweathermap.org/img/wn/" + this.imageName + "@2x.png";

            this.temp = data.main.temp;
          });
      }
    },

    getString(): string {
      const test = new Test();
      let str = test.getString();
      return str[3] + ", " + str[2] + " " + str[1] + " " + str[0];
    },
  },

  data() {
    return {
      dateStr: this.getString(),
      selectValue: "Montreal", //這裡負責select的初始化
      latitude: 0,
      longitude: 0,
      cityName: "Montreal",
      weather: "",
      temp: "0",
      image: "https://openweathermap.org/img/wn/" + this.imageName + "@2x.png",
    };
  },
});
</script>