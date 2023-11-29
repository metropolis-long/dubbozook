package org.bamboo.outerc.onfig.file;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.*;

public class OuterFileCachedProperty extends ConcurrentHashMap<String,Object> {

    private String outerFile;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private long lastTime=0l;

    public OuterFileCachedProperty(String outerFile) {
        this.outerFile = outerFile;
        init();
    }

    private void init(){
        try {
            refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.executorService.scheduleWithFixedDelay(()->{
            try {
                refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },0,2, TimeUnit.SECONDS);
    }

    private long refresh() throws IOException {
        File file = new File(this.outerFile);
        long last = file.lastModified();
        if (last>this.lastTime){
            List<String> allLines = Files.readAllLines(file.toPath());
            for (String l:allLines){
                //分割文件内容
                this.put(l.split("=")[0],l.split("=")[1]);
                System.out.println("外部文件修改，。，，，，，，，，，，，，，");
            }
            this.lastTime=last;
            return last;
        }
        return -1l;
    }

}
