package persistence;

import org.json.JSONObject;

// Code is obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
