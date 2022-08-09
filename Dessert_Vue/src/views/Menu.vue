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
        <ion-list v-for="(ml, index) in this.menuList" :key="index">
          <!-- {{  index}} -->
          <!-- {{ this.ml }} -->
          <!--这里图片不能设置50px style="width: 50px" 否则出现文字图片叠加情况-->
          <ion-item>
            <ion-avatar>
              <ion-img
                :src="this.menuList[index].imageUrl"
                @click="this.toDetail(this.menuList[index].id)"
              ></ion-img>
            </ion-avatar>
            <ion-label class="ion-text-wrap">{{
              this.menuList[index].name
            }}</ion-label>

            <!-- <ion-grid>
              <ion-row>
                <ion-col>
                  <ion-img
                    :src="this.menuList[index].imageUrl"
                    style="width: 50px"
                    @click="detail(this.ml.id)"
                  ></ion-img>
                </ion-col>
                <ion-col class = "itw">
                  <ion-label class="ion-text-wrap">{{
                    this.menuList[index].name
                  }}</ion-label>
                </ion-col>
                <ion-col>
                    </ion-col>
                   
                    
              </ion-row>
            </ion-grid> -->
          </ion-item>
        </ion-list>
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
  IonAvatar,
  IonItem,
IonLabel,

    

    IonList,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { Functions } from "../func/functions";
import { loadingController } from "@ionic/vue";
import { useRoute, useRouter } from "vue-router";
import { Meal } from "../func/common";
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
    IonAvatar,
    IonItem,
    IonLabel,
    IonList,
   
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    return { route, router };
  },

  methods: {
    async getMenuList() {
      const loading = await loadingController.create({
        message: "Attendre SVP...",
      });
      await loading.present();
      console.log("this.route.params.name:" + this.route.params.name);
      let url =
        "https://www.themealdb.com/api/json/v1/1/filter.php?c=" +
        this.route.params.name;
      const fun = new Functions();

      fetch(url)
        .then((reponse) => reponse.json())
        .then((data) => {
          console.log(data);

          const menuList = fun.getMenuList(data.meals);
          this.menuList = menuList;
          //   console.log("新的menulist是:"+menuList);

          //    for (let i=0 ;i<menuList.length;i++){
          //     console.log(".........." + menuList[i].id);
          //     console.log(".........." + menuList[i].name);

          // }

          loading.dismiss();
        });
    },
    
    toDetail(id:string){
      console.log("id is "+id);
      this.$router.push({
            path: "/detail",
            query: {
              id: id
              
            },
            
            })
    },
    ionViewWillEnter() {
      //this.getJSON();
    },

    ionViewDidEnter() {
      //this.getJSON();
      this.getMenuList();
    },
  },

  data() {
    return {
      menuList: [] as any[],
    };
  },
});
</script>

<style scoped>
/* .itw{
    padding-left: 0%;
    margin-left: 0%;
    left: 0%;
    align-items: flex-start;
} */

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
