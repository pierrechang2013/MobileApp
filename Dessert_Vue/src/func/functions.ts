import fetch from 'node-fetch';
import { Meal } from "./common";
export class Functions {

    //从json里把对应的都获取出来，并返回字符串
    getStr(json: any, key: string): string {
        
        const temp = json.meals[0]//取出json对象，这里就一层meals后面
        
        let result = "";
        // console.log(temp);
        for (const p in temp) {//这个就是只能单层遍历
            //console.log(p);
            //console.log("p.startsWith(key)" + p.startsWith(key));

            if (p.startsWith(key)) {
                if (temp[p] != null && temp[p].length > 0) {

                    result = result + "&"+ temp[p] ;
                }

            }
        }
        result = result.substring(1);
        console.log("Key is " + key + "and result is " + result);
        return result;

    }


    //转换字符串为数组
    getArray(str: string): Array<string> {

        let array = null;
        array = str.split("&");

        //array.pop();
        
        return array;
    }

    //获得list
    getMealList(url: string) {
        //https://www.themealdb.com/api/json/v1/1/search.php?f=c
        //应该有47个结果

        //let result;
        let mealList;

        fetch(url).then((res) =>

            res.json()).then((data) => {
                mealList = data;
                console.log(")))))))))))))))" + mealList);
                console.log(")))))))))))))))" + data);
            })


        return mealList;

    }
    //按catogorie获得列表
    getMenuList(menuList: JSON[]) {

        const newList: Array<Meal> = []
        console.log(menuList);

        for (let i = 0; i < menuList.length; i++) {


            const jo = JSON.parse(JSON.stringify(menuList[i]))

            const meal: Meal = {
                id: jo.idMeal, name: jo.strMeal, imageUrl: jo.strMealThumb
            };
            newList.push(meal);

        }

        console.log("newList.length:" + newList.length);



        return newList;




    }


    mergeArrays(array1: Array<any>, array2: Array<any>) {
          

        const length = array1.length <= array2.length ? array1.length : array2.length;
        const array: Array<any> = [];
        for(let i = 0;i<length;i++){
            array.push(array1[i]+" "+array2[i]);
        }

        return array;


    }



}