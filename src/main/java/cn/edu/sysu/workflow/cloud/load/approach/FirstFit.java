package cn.edu.sysu.workflow.cloud.load.approach;

import java.util.ArrayList;
import java.util.List;

public class FirstFit implements Allocator {
    @Override
    public void allocate(Integer[][] serverCapacityArray, List<List<Integer>> instanceBuffer, List<Integer> result) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < instanceBuffer.size(); i++) {
            for (int j = 0; j < serverCapacityArray.length; j++) {
                boolean success = true;
                for (int k = 0; k < instanceBuffer.get(i).size(); k++) {
                    if (instanceBuffer.get(i).get(k) > serverCapacityArray[j][k]) {
                        success = false;
                        break;
                    }
                }
                if (success) {
                    positions.add(j);
                    for (int k = 0; k < instanceBuffer.get(i).size(); k++) {
                        serverCapacityArray[j][k] -= instanceBuffer.get(i).get(k);
                    }
                    break;
                }
            }
            if (positions.size() < i + 1) {
                positions.add(-1);
            }
        }
        result.addAll(positions);
    }
}