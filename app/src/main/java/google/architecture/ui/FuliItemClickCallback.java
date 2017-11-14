
package google.architecture.ui;


import google.architecture.coremodel.datamodel.http.entities.FuliData;

public interface FuliItemClickCallback {
    void onClick(FuliData.ResultsBean fuliItem);
}
