package gelly.schemas;

import com.hanwencheng.ValueParameters;
import org.apache.flink.graph.Edge;

/**
 * Created by hanwencheng on 1/25/16.
 */
public class EdgeR<K, V> extends Edge<K, V> {
    private ValueParameters valueParameters;

    public EdgeR() {
        this.valueParameters = new ValueParameters();
    }

    public EdgeR(K src, K trg, V val) {
        super(src, trg, val);
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
