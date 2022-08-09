<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button color="primary"></ion-menu-button>
        </ion-buttons>
        <ion-title>{{ this.name }}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content>
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">MealDB</ion-title>
        </ion-toolbar>
      </ion-header>

      <div id="container">
        <ion-item>
          <ion-img :src="image" style="width: 100%"></ion-img>
        </ion-item>

        <ion-item>
          <ion-grid>
            <ion-row>
              <ion-col> Origine:{{ this.area }} </ion-col>
            </ion-row>
            <ion-row>
              <ion-col>Categorie:{{ this.category }} </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>

        <ion-item>
          <ion-col>
            <h3>Instructions</h3>
            <br />
            {{ this.instructions }}</ion-col
          >
        </ion-item>

        <ion-item>
          <ion-col
            ><h3>Ingredient:</h3>

            <!-- <ui> -->
              <ion-list v-for="(i, index) in this.ingredientArray" :key="index">
                <b>{{ index + 1 }}.{{ i }}</b>
              </ion-list>
            <!-- </ui> -->
          </ion-col>
        </ion-item>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonMenuButton,
  IonButtons,
  IonImg,
  IonCol,
  IonRow,
  IonGrid,
  IonList,
  IonItem,

} from "@ionic/vue";
import { defineComponent } from "vue";
import { Functions } from "../func/functions";
// import { onMounted, ref } from "vue";
// import { defineAsyncComponent } from "vue";
// import axios from "axios";
// import VueAxios from "vue-axios";
import { loadingController } from "@ionic/vue";

export default defineComponent({
  name: "HomePage",
  components: {
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
    IonMenuButton,
    IonButtons,
    IonImg,
    IonCol,
    IonRow,
    IonGrid,
    IonItem,
    IonList,
    
  },

  methods: {
    async getJSON() {
      const loading = await loadingController.create({
        message: "Attendre SVP...",
      });
      await loading.present();

      let url = "https://www.themealdb.com/api/json/v1/1/random.php";
      fetch(url)
        .then((reponse) => reponse.json())
        .then((data) => {
          console.log(data);
          //this.titre = data.titre_album;
          this.image = data.meals[0].strMealThumb;
          this.instructions = data.meals[0].strInstructions;
          var func = new Functions();
          this.ingredientArray = func.getArray(
            func.getStr(data, "strIngredient")
          );
          console.log(this.ingredientArray);
          this.ingMeasureArray = func.getArray(func.getStr(data, "strMeasure"));
console.log(this.ingMeasureArray);
          this.ingredientArray = func.mergeArrays(this.ingredientArray,this.ingMeasureArray);


          this.name = data.meals[0].strMeal;
          this.category = data.meals[0].strCategory;
          this.area = data.meals[0].strArea;
          loading.dismiss();
        });
    },
    ionViewWillEnter() {
      //this.getJSON();
    },

    ionViewDidEnter() {
      this.getJSON();
    },
  },

  data() {
    return {
      //mealList1: this.ml,\
      name: "",
      image: "",
      instructions: "",
      ingredientArray: [""],
      ingMeasureArray: [""],
      category: "",
      area: "",
    };
  },
});
</script>

<style scoped>
div > div {
  box-sizing: border-box;
  border: 2px solid #8c8c8c;
  width: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#container {
  text-align: center;

  position: absolute;
  align-items: center;
  /* left: 0;
  right: 0; */
  /* top: 50%; */
  /* transform: translateY(-50%); */
}

#container strong {
  font-size: 20px;
  line-height: 26px;
}

#container p {
  font-size: 16px;
  line-height: 22px;

  color: #8c8c8c;

  margin: 0;
}

#container a {
  text-decoration: none;
}
</style>
