package cn.com.common.util.concurrent.spmc;

import com.lmax.disruptor.EventHandler;

/**
 * 数据消费者代理<br/>
 * Created by wuliwei on 2015/6/14.
 *
 * @author wuliwei
 */
final class DataConsumerProxy implements DataConsumer, EventHandler<DataHolder> {
    private DataConsumer dataConsumer;

    public DataConsumerProxy(DataConsumer dataConsumer) {
        this.dataConsumer = dataConsumer;
    }

    public void handleData(Object data) {
        this.dataConsumer.handleData(data);
    }

    public void onEvent(DataHolder dataHolder, long sequence, boolean endOfBatch) throws Exception {
        if (null != dataHolder && null != dataHolder.getData()) {
            handleData(dataHolder.getData());
        }
    }
}