package mapwriter.tasks;

import mapwriter.MwUtil;
import mapwriter.region.MergeToImage;
import mapwriter.region.RegionManager;

import java.io.File;

public class MergeTask extends Task {
    private final RegionManager regionManager;
    private final File outputDir;
    private final String basename;
    private final int x, z, w, h, dimension;
    private String msg = "";

    public MergeTask(RegionManager regionManager, int x, int z, int w, int h, int dimension, File outputDir, String basename) {
        this.regionManager = regionManager;
        this.x = x;
        this.z = z;
        this.w = w;
        this.h = h;
        this.dimension = dimension;
        this.outputDir = outputDir;
        this.basename = basename;
    }

    @Override
    public void run() {
        int count = MergeToImage.merge(this.regionManager, this.x, this.z, this.w, this.h, this.dimension, this.outputDir, this.basename);
        if (count > 0) {
            this.msg = String.format("successfully wrote merged images to directory %s", this.outputDir);
        } else {
            this.msg = String.format("merge error: could not write images to directory %s", this.outputDir);
        }
    }

    @Override
    public void onComplete() {
        MwUtil.printBoth(this.msg);
    }

}
