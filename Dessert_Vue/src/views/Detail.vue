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

    <ion-content :fullscreen="true">
      <!-- <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Menu</ion-title>
        </ion-toolbar>
      </ion-header> -->

      <div id="container">
        <ion-item>
          <ion-img :src="image"></ion-img>
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
  IonImg,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonMenuButton,
  IonButtons,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { useRoute, useRouter } from "vue-router";
import { Functions } from "../func/functions";
import { loadingController } from "@ionic/vue";

export default defineComponent({
  name: "HomePage",
  components: {
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
    IonImg,
    IonCol,
    IonRow,
    IonGrid,
    IonList,
    IonItem,
    IonMenuButton,
    IonButtons,
  },
  setup() {
    const route = useRoute();
    //const id = route.query.id;
    //console.log("details 获得 id:"+id)
    // var func = new Functions();
    // const router = useRouter();
    // const goBack = () => {
    //   router.go(-1);
    // };
    // const route = useRoute();
    // const id = route.query.id;
    // const name = route.query.name;
    // const category = route.query.category;
    // const area = route.query.area;
    // const instructions = route.query.instructions;
    // let ingredient = "";
    // if (route.query.ingredient != null) {
    //   ingredient = route.query.ingredient!.toString();
    // }
    // const image = route.query.image;
    // const ingredientArray = func.getArray(ingredient);
    // //console.log(id);
    // return {
    //   id,
    //   goBack,
    //   name,
    //   category,
    //   area,
    //   instructions,
    //   ingredient,
    //   image,
    //   ingredientArray,
    //};
    return { route };
  },
  data() {
    return {
      // name: null,
      image: "",
      name: "",
      category: "",
      area: "",
      instructions: "",
      ingredient: "",
      ingredientArray: [] as any[],
      ingMeasureArray: [] as any[],
    };
  },
  methods: {
    async getJSON() {
      const id = this.route.query.id;
      const loading = await loadingController.create({
        message: "Attendre SVP...",
      });
      await loading.present();
      let url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
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
          this.ingMeasureArray = func.getArray(func.getStr(data, "strMeasure"));

          this.ingredientArray = func.mergeArrays(this.ingredientArray,this.ingMeasureArray);

          this.name = data.meals[0].strMeal;
          this.category = data.meals[0].strCategory;
          this.area = data.meals[0].strArea;
          loading.dismiss();
        });
    },
    ionViewDidEnter() {
      this.getJSON();  
    },

    ionViewWillEnter() {
      //console.log("ionViewWillEnter 获得 id:"+this.id)
      //this.getJSON();
    },
  },
});
</script>

<style scoped>
#container {
  text-align: center;

  position: absolute;
  left: 0;
  right: 0;
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
