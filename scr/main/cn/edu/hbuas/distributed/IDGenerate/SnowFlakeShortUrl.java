package cn.edu.hbuas.distributed.IDGenerate;




/**
 * @author luopengfei
 */
public class SnowFlakeShortUrl {
    /**
    * 起始时间戳
     */
    private final static  long START_TIMESTAMP = 1480166465631L;
    /**
     * 序列号占用位数
     */
    private final  static  long SEQUENCE_BIT = 12;
    /**
     *机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     *数据中心占用的位数
     */
    private final static long DATA_CENTER_BIT = 5;
    /**
     *每部分的最大值
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    /**
     *每一部分向左位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    /**
     *数据中心
     */
    private long dataCenterId;
    /**
     *机器标识
     */
    private long machineId;
    /**
     *序列号
     */
    private long sequence = 0L;
    /**
     *上一次时间戳
     */
    private long lastTimeStamp = -1L;


    /**
     *
     *
     * @return 返回下一个时间段
     */
    private  long getNextMill(){
        long mill = getNextMill();
        while(mill <= lastTimeStamp){
            mill = getNewTimeStamp();
        }
        return mill;
    }

    private long getNewTimeStamp(){
        return System.currentTimeMillis();
    }

    /**
     * 根据指定的数据中信ID和机器中心标志ID生成指定的序列号
     *
     * @param dataCenterId 机器中心ID
     * @param machineId  机器标志ID
     */
    public SnowFlakeShortUrl(long dataCenterId, long machineId){
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("MachineId can't be greater than MAX_MACHINE_NUM or less than 0！");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public synchronized  long nextId(){
        long currTimeStamp = getNewTimeStamp();
        if(currTimeStamp < lastTimeStamp){
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if(currTimeStamp == lastTimeStamp){
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if(sequence == 0L){
                currTimeStamp = getNextMill();
            }
        }
        else{
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastTimeStamp = currTimeStamp;
        return (currTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT
                | dataCenterId << DATA_CENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }



    public static void main(String[] args) {
        SnowFlakeShortUrl snowFlake = new SnowFlakeShortUrl(2, 3);

        for (int i = 0; i < (1 << 4); i++) {
            //10进制
            System.out.println(snowFlake.nextId());
        }
        System.out.println(System.currentTimeMillis());
    }

}
