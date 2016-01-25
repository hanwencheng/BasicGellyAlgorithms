package gelly.schemas;

import com.hanwencheng.ValueParameters;
import org.apache.flink.graph.Vertex;

/**
 * Created by hanwencheng on 1/25/16.
 */
public class VertexR<K, V> extends Vertex<K, V>{
    private ValueParameters valueParameters;

    public VertexR() {
        this.valueParameters = new ValueParameters();
    }

    public VertexR(K k, V val) {
        super(k, val);
        this.valueParameters = new ValueParameters();
    }

    public void addParameter(String name, Object parameter){
        this.valueParameters.create(name, parameter);
    }

    public void deleteParameter(String name){
        this.valueParameters.delete(name);
    }

    public void deleteParameter(String name, Object parameter){
        this.valueParameters.delete(name, parameter);
    }

    public void getParameter(String name){
        this.valueParameters.get(name);
    }
}
