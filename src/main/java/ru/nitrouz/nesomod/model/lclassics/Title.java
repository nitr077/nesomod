package ru.nitrouz.nesomod.model.lclassics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sort_title",
        "publisher",
        "code",
        "rom",
        "copyright",
        "title",
        "volume",
        "release_date",
        "players_count",
        "cover",
        "overscan",
        "armet_version",
        "lcla6_release_date",
        "save_count",
        "simultaneous",
        "fadein",
        "details_screen",
        "armet_threshold",
        "sort_publisher"
})
public class Title {

    @JsonProperty("sort_title")
    private String sortTitle;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rom")
    private String rom;
    @JsonProperty("copyright")
    private String copyright;
    @JsonProperty("title")
    private String title;
    @JsonProperty("volume")
    private Integer volume;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("players_count")
    private Integer playersCount;
    @JsonProperty("cover")
    private String cover;
    @JsonProperty("overscan")
    private List<Integer> overscan = null;
    @JsonProperty("armet_version")
    private String armetVersion;
    @JsonProperty("lcla6_release_date")
    private String lcla6ReleaseDate;
    @JsonProperty("save_count")
    private Integer saveCount;
    @JsonProperty("simultaneous")
    private Boolean simultaneous;
    @JsonProperty("fadein")
    private List<Integer> fadein = null;
    @JsonProperty("details_screen")
    private String detailsScreen;
    @JsonProperty("armet_threshold")
    private Integer armetThreshold;
    @JsonProperty("sort_publisher")
    private String sortPublisher;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sort_title")
    public String getSortTitle() {
        return sortTitle;
    }

    @JsonProperty("sort_title")
    public void setSortTitle(String sortTitle) {
        this.sortTitle = sortTitle;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("rom")
    public String getRom() {
        return rom;
    }

    @JsonProperty("rom")
    public void setRom(String rom) {
        this.rom = rom;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("volume")
    public Integer getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("players_count")
    public Integer getPlayersCount() {
        return playersCount;
    }

    @JsonProperty("players_count")
    public void setPlayersCount(Integer playersCount) {
        this.playersCount = playersCount;
    }

    @JsonProperty("cover")
    public String getCover() {
        return cover;
    }

    @JsonProperty("cover")
    public void setCover(String cover) {
        this.cover = cover;
    }

    @JsonProperty("overscan")
    public List<Integer> getOverscan() {
        return overscan;
    }

    @JsonProperty("overscan")
    public void setOverscan(List<Integer> overscan) {
        this.overscan = overscan;
    }

    @JsonProperty("armet_version")
    public String getArmetVersion() {
        return armetVersion;
    }

    @JsonProperty("armet_version")
    public void setArmetVersion(String armetVersion) {
        this.armetVersion = armetVersion;
    }

    @JsonProperty("lcla6_release_date")
    public String getLcla6ReleaseDate() {
        return lcla6ReleaseDate;
    }

    @JsonProperty("lcla6_release_date")
    public void setLcla6ReleaseDate(String lcla6ReleaseDate) {
        this.lcla6ReleaseDate = lcla6ReleaseDate;
    }

    @JsonProperty("save_count")
    public Integer getSaveCount() {
        return saveCount;
    }

    @JsonProperty("save_count")
    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    @JsonProperty("simultaneous")
    public Boolean getSimultaneous() {
        return simultaneous;
    }

    @JsonProperty("simultaneous")
    public void setSimultaneous(Boolean simultaneous) {
        this.simultaneous = simultaneous;
    }

    @JsonProperty("fadein")
    public List<Integer> getFadein() {
        return fadein;
    }

    @JsonProperty("fadein")
    public void setFadein(List<Integer> fadein) {
        this.fadein = fadein;
    }

    @JsonProperty("details_screen")
    public String getDetailsScreen() {
        return detailsScreen;
    }

    @JsonProperty("details_screen")
    public void setDetailsScreen(String detailsScreen) {
        this.detailsScreen = detailsScreen;
    }

    @JsonProperty("armet_threshold")
    public Integer getArmetThreshold() {
        return armetThreshold;
    }

    @JsonProperty("armet_threshold")
    public void setArmetThreshold(Integer armetThreshold) {
        this.armetThreshold = armetThreshold;
    }

    @JsonProperty("sort_publisher")
    public String getSortPublisher() {
        return sortPublisher;
    }

    @JsonProperty("sort_publisher")
    public void setSortPublisher(String sortPublisher) {
        this.sortPublisher = sortPublisher;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}