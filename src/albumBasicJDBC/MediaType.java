package albumBasicJDBC;

public class MediaType {
    private int mediaTypeId;
    private String type;

    public MediaType(int mediaTypeId, String type) {
        this.mediaTypeId = mediaTypeId;
        this.type = type;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "mediaTypeId=" + mediaTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}
