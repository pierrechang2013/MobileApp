package canada.montreal.pierre.judocanada;

import com.google.gson.Gson;


public class Functions {

    Film getFilmObject(String jsonData){

        Gson gson = new Gson();
        Film film = gson.fromJson(jsonData,Film.class);

        return film;

    }


    Member getMemberObject(String jsonData){

        Gson gson = new Gson();
        Member member = gson.fromJson(jsonData,Member.class);

        return member;

    }




}
