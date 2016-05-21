package com.feed_the_beast.ftbu.world;

import com.feed_the_beast.ftbl.api.ForgePlayer;
import com.feed_the_beast.ftbl.util.PrivacyLevel;
import latmod.lib.IntMap;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by LatvianModder on 23.02.2016.
 */
public class FTBUPlayerDataSP extends FTBUPlayerData
{
    public short claimedChunks, loadedChunks, maxClaimedChunks, maxLoadedChunks;

    @Override
    public FTBUPlayerDataSP toSP()
    {
        return this;
    }

    @Override
    public void readSyncData(ForgePlayer player, NBTTagCompound tag, boolean self)
    {
        IntMap map = new IntMap();
        map.list.setDefVal(0);
        map.list.addAll(tag.getIntArray("F"));

        flags = (byte) map.get(0);
        blocks = PrivacyLevel.VALUES_3[map.get(1)];

        if(self)
        {
            claimedChunks = (short) map.get(10);
            loadedChunks = (short) map.get(11);
            maxClaimedChunks = (short) map.get(12);
            maxLoadedChunks = (short) map.get(13);
        }
    }
}
