package MyMathInterpreter;

import MyMathInterpreter.interpreters.OptimizeFunctionInterpreter;
import MyMathInterpreter.interpreters.RungeKuttaInterpreter;
import MyMathInterpreter.interpreters.TrapezeMethodInterpreter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.inject.Named;
//import javax.servlet.ServletException;

/**
 * Created by Δενθρ on 23.02.2016.
 */

//@Named
@ManagedBean
@SessionScoped
public class InputCommandBean implements Serializable {
    private String inputData = null;
    String response = null;

    public InputCommandBean() {
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public List<String> getResponse() {
        List<String> response = new LinkedList<>();
        /*if(inputData == null || inputData.length() == 0) {
            return "Enter anything!";
        } else {
            return getInputData();
        }
    }not to Ajax*/
        if(inputData == null) {
            return null;
        } else if(inputData.length() == 0) {
            response.add("Enter anything!");
            return response;
        } else  {
            //Write code here, and return answer
            //return getInputData();
            try {
                //Interpreter interpreter = new RungeKuttaInterpreter();
                //Interpreter interpreter = new TrapezeMethodInterpreter();
                Interpreter interpreter = new OptimizeFunctionInterpreter();
                return interpreter.solution(getInputData());
                //return "20.11.2016";
            } catch (NullPointerException npe) {
                response.add("Null Pointer Exception");
                return response;
            } catch (Exception e) {
                response.add("Exception");
                response.add(e.getMessage());
                return response;
            }
        }
    }
}
