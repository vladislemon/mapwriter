package mapwriter.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mapwriter.Mw;
import mapwriter.map.MapView;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class MwGuiDimensionDialog extends MwGuiTextDialog {

    private final Mw mw;
    private final MapView mapView;

    public MwGuiDimensionDialog(GuiScreen parentScreen, Mw mw, MapView mapView, int dimension) {
        super(parentScreen, I18n.format("mw.gui.mwguidimensiondialog.title") + ":", Integer.toString(dimension), I18n.format("mw.gui.mwguidimensiondialog.error"));
        this.mw = mw;
        this.mapView = mapView;
    }

    @Override
    public boolean submit() {
        boolean done = false;
        int dimension = this.getInputAsInt();
        if (this.inputValid) {
            this.mapView.setDimensionAndAdjustZoom(dimension);
            this.mw.miniMap.view.setDimension(dimension);
            this.mw.addDimension(dimension);
            done = true;
        }
        return done;
    }
}
