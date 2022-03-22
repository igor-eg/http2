import com.fasterxml.jackson.annotation.JsonProperty;

public class Nasa {
    //{"copyright":"Tom Glenn","date":"2022-01-27","explanation":"The //// reversed.",
    // "hdurl":"https://apod.nasa.gov/apod/image/2201/Mare_Orientale_Nov_27_2021_TGlenn_fullsize.jpg",
    // "media_type":"image","service_version":"v1","title":"Western Moon, Eastern Sea",
    // "url":"https://apod.nasa.gov/apod/image/2201/Mare_Orientale_Nov_27_2021_TGlenn_1024.jpg"}
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media_type;
    private final String service_version;
    private final String title;
    private final String url;


    public Nasa(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }


    public String getСopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }


    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Nasa" +
                "\n  copyright=" + copyright +
                "\n  date=" + date + "" +
                "\n  explanation=" + explanation + "" +
                "\n  hdurl=" + hdurl + "" +
                "\n  media_type=" + media_type + "" +
                "\n  service_version=" + service_version + "" +
                "\n  title=" + title + "" +
                "\n  url=" + url;

    }

}
