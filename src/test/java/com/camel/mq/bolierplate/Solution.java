
// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.text.SimpleDateFormat;
import java.util.*;

class Solution {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String solution(final String S) {
        if (S == null) {
            return null;
        }
        final Map<String, List<PhotoData>> locationGroup = new HashMap<>();
        final Map<String, PhotoData> inputPhotoDataMapper = new HashMap<>();
        final String[] ins = S.split("\n");
        for(String p: ins) {
            final PhotoData photoData = new PhotoData(p);
            final List<PhotoData> list = locationGroup.getOrDefault(photoData.location, new ArrayList<>());
            list.add(photoData);
            locationGroup.put(photoData.location, list);
            inputPhotoDataMapper.put(p, photoData);
        }

        locationGroup.forEach(
                (k, v) -> {
                    Collections.sort(v, new PhotoDataComparator());
                    for(int i = 0 ; i < v.size() ; i ++) {
                        v.get(i).assignNumber(i + 1, v.size());
                    }
                }
        );
        final StringBuilder stringBuilder = new StringBuilder();
        for (String p: ins) {
            stringBuilder.append(inputPhotoDataMapper.get(p).getFormattedName()).append("\n");
        }
        return stringBuilder.toString();
    }

    public class PhotoData {
        String name;
        String extension;
        String location;
        Date date;
        String number;

        public PhotoData(String s) {
            final String[] d = s.split(", ");
            name = d[0].split("\\.")[0];
            extension = d[0].split("\\.")[1];
            location = d[1];
            try {
                date = dateFormatter.parse(d[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void assignNumber(int number, int numberOfItems) {
            if(number < 10 && numberOfItems >= 10) {
                this.number = "0" + number;
            } else {
                this.number = String.valueOf(number);
            }
        }

        public String getFormattedName() {
            return location + number + "." + extension;
        }

    }

    public class PhotoDataComparator implements Comparator<PhotoData> {
        @Override
        public int compare(final PhotoData o1, final PhotoData o2) {
            return o1.date.compareTo(o2.date);
        }
    }

}

