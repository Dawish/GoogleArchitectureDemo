
package google.architecture.news;


import google.architecture.coremodel.datamodel.http.entities.NewsData;

public interface NewsItemClickCallback {
    void onClick(NewsData.ResultsBean newsItem);
}
