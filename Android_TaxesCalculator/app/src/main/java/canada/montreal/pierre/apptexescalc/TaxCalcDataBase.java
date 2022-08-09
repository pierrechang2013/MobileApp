package canada.montreal.pierre.apptexescalc;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/*
 * @Database标签用于告诉系统这是Room数据库对象。
 * entities属性用于指定该数据库有哪些表，若需建立多张表，以逗号相隔开。
 * version属性用于指定数据库版本号，后续数据库的升级正是依据版本号来判断的。
 * 该类需要继承自RoomDatabase，在类中，通过Room.databaseBuilder()结合单例设计模式，完成数据库的创建工作。
 * 另外，我们创建的Dao对象，在这里以抽象方法的形式返回，只需一行代码即可。
 *
 * */

@Database(entities = {Bill.class}, version = 1)
public abstract class TaxCalcDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "taxcalc";

    public abstract ProjectDAO getProductDao();

    private static TaxCalcDataBase dataBaseInstance;

    public static synchronized TaxCalcDataBase getInstance(Context context){

        if(dataBaseInstance==null){

            dataBaseInstance = Room.databaseBuilder(context.getApplicationContext(), TaxCalcDataBase.class,DATABASE_NAME).build();
        }

        return dataBaseInstance;

    }

    // Database class after the version update.






}
