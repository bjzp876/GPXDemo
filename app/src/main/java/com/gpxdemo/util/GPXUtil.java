package com.gpxdemo.util;

import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Route;
import org.alternativevision.gpx.beans.Track;
import org.alternativevision.gpx.beans.Waypoint;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * GPX解析
 * Created by Administrator on 2017/5/29.
 */

public class GPXUtil {

    /** GPX解析 **/
    public static List<Waypoint> getGPX(String path){
        GPXParser p = new GPXParser();
        List<Waypoint> list = null;
        try {
            FileInputStream in = new FileInputStream(path);
            GPX gpx = p.parseGPX(in);
            gpx.getWaypoints();
            gpx.getRoutes();
            if(gpx.getTracks()!=null){
                list = getTrackList(gpx.getTracks());
            }else{
                list = getRouteList(gpx.getRoutes());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<Waypoint> getRouteList(HashSet<Route> hashSet){
        List<Waypoint> list = new ArrayList<>();
        if (!hashSet.isEmpty()){
            Iterator<Route> iterator = hashSet.iterator();
            while (iterator.hasNext()){
                Route track = iterator.next();
                list.addAll(track.getRoutePoints());
            }
        }
        return list;
    }

    private static List<Waypoint> getTrackList(HashSet<Track> hashSet){
        List<Waypoint> list = new ArrayList<>();
        if (!hashSet.isEmpty()){
            Iterator<Track> iterator = hashSet.iterator();
            while (iterator.hasNext()){
                Track track = iterator.next();
                list.addAll(track.getTrackPoints());
            }
        }
        return list;
    }
}
