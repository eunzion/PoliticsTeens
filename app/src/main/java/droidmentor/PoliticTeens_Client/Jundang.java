package droidmentor.PoliticTeens_Client;

/**
 * Created by User on 2017-09-16.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Jundang {
    public String just_name;
    public String just_category;
    public String just_idea;

    public Jundang(){
    }

    public Jundang(String just_name, String just_category, String just_idea){
        this.just_name=just_name;
        this.just_category=just_category;
        this.just_idea=just_idea;
    }

    @Exclude
    public Map<String,Object> toMap()
    {
        HashMap<String,Object> result=new HashMap<>();
        result.put("category",just_category);
        result.put("name",just_name);
        result.put("idea",just_idea);

        return result;
    }
}

