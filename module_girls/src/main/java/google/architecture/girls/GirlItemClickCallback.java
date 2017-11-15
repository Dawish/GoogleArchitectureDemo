
package google.architecture.girls;


import google.architecture.coremodel.datamodel.http.entities.GirlsData;

public interface GirlItemClickCallback {
    void onClick(GirlsData.ResultsBean girlsItem);
}
