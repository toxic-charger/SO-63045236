package com.stackoverflow.q63045236;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.stackoverflow.q63045236.model.POJOContent;
import com.stackoverflow.q63045236.model.POJOListItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private final String JSON = "{\n"
      + "  \"abc\": {\n"
      + "    \"1\": {\n"
      + "      \"1\": {\n"
      + "        \"ab\": \"some content\",\n"
      + "        \"id\": \"240\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"2\": {\n"
      + "        \"ab\": \"some content\",\n"
      + "        \"id\": \"240\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"3\": {\n"
      + "        \"ab\": \"some content\",\n"
      + "        \"id\": \"240\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"4\": {\n"
      + "        \"ab\": \"some content\",\n"
      + "        \"id\": \"240\",\n"
      + "        \"key\": \"value\"\n"
      + "      }\n"
      + "    },\n"
      + "    \"2\": {\n"
      + "      \"1\": {\n"
      + "        \"ab\": \"more content\",\n"
      + "        \"id\": \"241\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"2\": {\n"
      + "        \"ab\": \"more content 1\",\n"
      + "        \"id\": \"241\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"3\": {\n"
      + "        \"ab\": \"more content 2\",\n"
      + "        \"id\": \"241\",\n"
      + "        \"key\": \"value\"\n"
      + "      }\n"
      + "    },\n"
      + "    \"3\": {\n"
      + "      \"1\": {\n"
      + "        \"ab\": \"more content\",\n"
      + "        \"id\": \"242\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"2\": {\n"
      + "        \"ab\": \"more content 1\",\n"
      + "        \"id\": \"243\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"3\": {\n"
      + "        \"ab\": \"more content 2\",\n"
      + "        \"id\": \"242\",\n"
      + "        \"key\": \"value\"\n"
      + "      }\n"
      + "    },\n"
      + "    \"4\": {\n"
      + "      \"1\": {\n"
      + "        \"ab\": \"more content\",\n"
      + "        \"id\": \"245\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"2\": {\n"
      + "        \"ab\": \"more content 1\",\n"
      + "        \"id\": \"246\",\n"
      + "        \"key\": \"value\"\n"
      + "      },\n"
      + "      \"3\": {\n"
      + "        \"ab\": \"more content 2\",\n"
      + "        \"id\": \"247\",\n"
      + "        \"key\": \"value\"\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MyContentWrapper contentWrapper = new Gson().fromJson(JSON, MyContentWrapper.class);

    List<POJOListItem> pojo = new ArrayList<>();

    // map MyContentWrapper object to List<POJOListItem>
    for (Map.Entry<Integer, Map<Integer, POJOContent>> mapEntry : contentWrapper.abc.entrySet()) {
      for (Map.Entry<Integer, POJOContent> pojoContentEntry : mapEntry.getValue().entrySet()) {
        // Added only if content = "ab" is not blank.
        if (pojoContentEntry.getValue().getContent() != null || !pojoContentEntry.getValue()
            .getContent()
            .isEmpty()) {
          // check if the list contains item with the current POJOContent.id, if its present then get it
          POJOListItem pojoListItem = null;
          for (POJOListItem item : pojo) {
            if (item.id.equals(pojoContentEntry.getValue().getId())) {
              pojoListItem = item;
              break;
            }
          }
          // if list does not contains the item then create a new one, and if its present add the
          // contents and key values to it
          if (pojoListItem == null) {
            pojoListItem = new POJOListItem();
            pojoListItem.id = pojoContentEntry.getValue().getId();
            pojoListItem.contents.add(pojoContentEntry.getValue().content);
            pojoListItem.keys.add(pojoContentEntry.getValue().key);
            pojo.add(pojoListItem);
          } else {
            pojoListItem.contents.add(pojoContentEntry.getValue().content);
            pojoListItem.keys.add(pojoContentEntry.getValue().key);
          }
        }
      }
    }


    // TODO: test with 50000 list items

    POJOListItem pojoListItem = pojo.get(0);
    for (int i = 0; i < 50000; i++) {
      pojo.add(pojoListItem);
    }


    RecyclerView recyclerView = findViewById(R.id.rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new MyContentViewAdapter(pojo));
  }
}