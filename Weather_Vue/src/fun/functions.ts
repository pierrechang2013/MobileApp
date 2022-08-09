import { Geolocation } from '@capacitor/geolocation';


export class Test {

    formatDate(): string[] {
        const d = new Date();
        const year = d.getFullYear();
        const month = d.getMonth() + 1;
        const date = d.getDate();
        const day = d.getDay();

        return [year.toString(), month.toString(), date.toString(), day.toString()];
    }

    getString(): string[] {

        const fds = this.formatDate();
        let month = "";
        let date = "";
        const day = fds[2];
        switch (fds[1]) {//月
            case "1": {
                month = "jan";
                break;
            }
            case "2": {
                month = "fev";
                break;
            }
            case "3": {
                month = "mars";
                break;
            }
            case "4": {
                month = "avril";
                break;
            }
            case "5": {
                month = "mai";
                break;
            }
            case "6": {
                month = "juin";
                break;
            }
            case "7": {
                month = "juillet";
                break;
            }
            case "8": {
                month = "août";
                break;
            }
            case "9": {
                month = "step";
                break;
            }
            case "10": {
                month = "oct";
                break;
            }
            case "11": {
                month = "nov";
                break;
            }
            case "12": {
                month = "dec";
                break;
            }
            default: {

                console.log("sais pas");
                break;
            }
        }

        switch (fds[3]) {//星期
            case "1": {
                date = "Lundi";
                break;
            }
            case "2": {
                date = "Mardi";
                break;
            }
            case "3": {
                date = "Mercredi";
                break;
            }
            case "4": {
                date = "Jeudi";
                break;
            }
            case "5": {
                date = "Vendredi";
                break;
            }
            case "6": {
                date = "Samdi";
                break;
            }
            case "7": {
                date = "Dimanch";
                break;
            }

            default: {

                console.log("sais pas");
                break;
            }
        }
        console.log([fds[0], month, date, day])
        return [fds[0], month, day,date];
    }

    async getCurrentPosition(): Promise<City> {
        const coordinates = await Geolocation.getCurrentPosition();
        console.log('Current', coordinates);
        const latitude = coordinates.coords.latitude;
        const longitude = coordinates.coords.longitude;

        return new City(latitude, longitude);

    }

    async  loadMeteoByName(name: string):Promise<string[]> {
        const api_key = '486369f7fd75ed0b2103fef6226f0f14';
        const url = 'http://api.openweathermap.org/data/2.5/weather?q=' + name + ',ca&mode=json&units=metric&appid=' + api_key;
        const meteo:string[] = [];

        fetch(url)
            .then(

                (reponse) =>  reponse.json()

            )
            .then(

                (data) => {

                    //console.log(data);
                    //console.log(data.weather);
                    console.log("load......"+data.weather[0].main);
                    console.log("load......" +data.weather[0].icon);
                    meteo[0] = data.weather[0].main
                    meteo[1] = data.weather[0].icon
                }
            );

            return meteo;

    }

    loadMeteoByCoo(lat: string, lon: string) {
        const api_key = '486369f7fd75ed0b2103fef6226f0f14';
        const url = "https://api.openweathermap.org/data/2.5/weather?lat={" + lat + "}&lon={" + lon + "}&limit={1}&appid={" + api_key + "}";
        fetch(url)
            .then(

                (reponse) => reponse.json()

            )
            .then(

                (data) => {

                    console.log(data);


                }
            );


    }



}

class City {
    //  latitude:string;
    //  longitude:string;

    public constructor(private latitude: number, private longitude: number) {

    }

    public getLatitude(): number {
        return this.latitude;
    }

    public getLongitude(): number {
        return this.longitude;
    }

}

// const t = new Test();

// console.log(t.formatDate());


