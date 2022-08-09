<template>
  <ion-app>
    <ion-split-pane content-id="main-content">
      <ion-menu side="start" menu-id="first" content-id="main-content">
        <ion-header>
          <ion-toolbar color="primary">
            <ion-title>Menu</ion-title>
          </ion-toolbar>
        </ion-header>

        <ion-content>
          <ion-list>
            <!-- <ion-list-header>Mon menu</ion-list-header> -->

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/home">Accueil</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Beef">Beef</ion-item>
            </ion-menu-toggle>
            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Chicken">Chicken</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Dessert">Dessert</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Lamb">Lamb</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Miscellaneous">Miscellaneous</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Pasta">Pasta</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Pork">Pork</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Seafood">Seafood</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Side">Side</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Starter">Starter</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Vegan">Vegan</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Vegetarian">Vegetarian</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Breakfast">Breakfast</ion-item>
            </ion-menu-toggle>

            <ion-menu-toggle>
              <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
              <ion-item router-link="/menu/Goat">Goat</ion-item>
            </ion-menu-toggle>

            

            <ion-menu-toggle>
              <ion-item>
                <label class="item item-input">
                  <input
                    v-model="form.contenu"
                    type="text"
                    placeholder="Name,Id,Catogory..."
                  />
                </label>
                <ion-button id="submitBtn" color="primary" @click="submit">
                  cherche</ion-button
                ></ion-item
              >
            </ion-menu-toggle>

            <!-- <ion-menu-toggle>
              <ion-item>
                <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>
                <ion-label>Page 1</ion-label>
              </ion-item>

              <ion-item>
                <ion-icon slot="start" :ios="logoTux" :md="logoTux"></ion-icon>

                <ion-label>Page 2</ion-label>
              </ion-item>

            </ion-menu-toggle> -->
          </ion-list>
        </ion-content>
      </ion-menu>
      <ion-router-outlet id="main-content"></ion-router-outlet>
    </ion-split-pane>
  </ion-app>
</template>

 <script lang="ts">
import {
  IonApp,
  IonRouterOutlet,
  IonSplitPane,
  IonContent,
  IonList,
  // IonListHeader,
  IonItem,
  // IonLabel,
  IonMenu,
  IonMenuToggle,
  IonIcon,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonButton,
  
  
} from "@ionic/vue";
import { defineComponent } from "vue";
import { Functions } from "./func/functions";

// Importer les icones
import { logoTux } from "ionicons/icons";
import { alertController } from "@ionic/core";

export default defineComponent({
  name: "App",
  components: {
    IonApp,
    IonRouterOutlet,
    IonSplitPane,
    IonContent,
    IonList,
    //IonListHeader,
    IonItem,
    //IonLabel,
    IonMenu,
    IonMenuToggle,
    IonIcon,
    IonHeader,
    IonTitle,
    IonToolbar,
    IonButton,
  },
  setup() {
    return {
      logoTux,
    };
  },

  data() {
    return {
      form: {
        //email: null,
        contenu: null,
      },
    };
  },
  methods: {
    submit() {
      let url =
        "https://www.themealdb.com/api/json/v1/1/search.php?s=" +
        this.form.contenu;
      console.log(url);

      fetch(url).then((response) => {
        response.json().then((data) => {
          if (data.meals == null) {
            alertController
              .create({
                header: "Alert",
                subHeader: "",
                message: this.form.contenu + " ne existe pas.Essayez encore ",
                buttons: ["OK"],
              })
              .then((a) => a.present());
          }
          // console.log(data.meals[0].idMeal);
          // console.log(data.meals[0].strMeal);
          // console.log(data.meals[0].strCategory);
          // console.log(data.meals[0].strArea);
          console.log(console.log(data));

          //在这跳转，要不异步了，没拿到参数就跳走了
          //页面通过路由进行跳转，传递参数
          //this.$router.push({ path: "/detail", params: { id: this.xxxx } });
          //使用query传递对象
          const func = new Functions();
          this.$router.push({
            path: "/detail",
            query: {
              id: data.meals[0].idMeal,
              name: data.meals[0].strMeal,
              category: data.meals[0].strCategory,
              area: data.meals[0].strArea,
              instructions: data.meals[0].strInstructions,
              ingredient: func.getStr(data, "strIngredient"), //把对应内容提取出来，然后获得组成对应字符串
              image: data.meals[0].strMealThumb,
            },
          });

          //另一个页面进行接收
          //let id = this.$route.params.id
        });
      });
    },
  },
});
</script>

<style scoped>
#submitBtn {
  text-align: center;

  font-size: 10px;
}
</style>