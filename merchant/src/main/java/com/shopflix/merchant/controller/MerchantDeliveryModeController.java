package com.shopflix.merchant.controller;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.response.ApiResult;
import com.shopflix.merchant.facades.delivery.MerchantDeliveryModeFacade;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import com.shopflix.merchant.forms.DeliveryModeForm;
import com.shopflix.merchant.forms.DeliveryModeValueForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchant/api/v1/delivery-mode")
public class MerchantDeliveryModeController extends AbstractController
{

    @Resource(name = "merchantDeliveryModeFacade")
    private MerchantDeliveryModeFacade merchantDeliveryModeFacade;


    @GetMapping
    public ResponseEntity<ApiResult<List<DeliveryModeData>>> deliveryModes() {
        final List<DeliveryModeData> allDeliveryModes = merchantDeliveryModeFacade.getAllDeliveryModes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(allDeliveryModes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<DeliveryModeData>> deliveryModeDetail(@PathVariable("id") Long deliveryModeId) {
        DeliveryModeData deliveryModeData = merchantDeliveryModeFacade.getDeliveryModeForId(deliveryModeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(deliveryModeData));
    }

    @PostMapping
    public ResponseEntity<ApiResult<DeliveryModeData>> postDeliveryMode(@RequestBody DeliveryModeForm form) {

        DeliveryModeData data = convertDeliveryModeFormToData(form);

        final DeliveryModeData deliveryModeData = merchantDeliveryModeFacade.postDeliveryMode(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(deliveryModeData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<DeliveryModeData>> editDeliveryMode(@PathVariable("id") Long deliveryModeId, @RequestBody DeliveryModeForm form) {

        DeliveryModeData data = convertDeliveryModeFormToData(form);

        final DeliveryModeData deliveryModeData = merchantDeliveryModeFacade.editDeliveryMode(deliveryModeId, data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(deliveryModeData));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDeliveryMode(@PathVariable("id") Long deliveryModeId) {
        merchantDeliveryModeFacade.removeDeliveryMode(deliveryModeId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


    private DeliveryModeData convertDeliveryModeFormToData(@RequestBody DeliveryModeForm form)
    {
        DeliveryModeData data = new DeliveryModeData();
        data.setId(form.getId());
        data.setCode(form.getCode());
        data.setName(form.getName());
        data.setModeType(form.getModeType());
        data.setModeSubType(form.getModeSubType());
        data.setTemperatureType(form.getTemperatureType());
        data.setActive(form.getActive());

        List<DeliveryModeValueData> valueDataList = new ArrayList<>();

        final List<DeliveryModeValueForm> tierConditions = form.getTierConditions();
        for (DeliveryModeValueForm valueForm : tierConditions) {
            DeliveryModeValueData valueData = new DeliveryModeValueData();
            valueData.setId(valueForm.getId());
            valueData.setMinOrderTotal(valueForm.getMinOrderTotal());
            valueData.setDeliveryCost(valueForm.getDeliveryCost());
            valueDataList.add(valueData);
        }

        data.setTierConditions(valueDataList);
        return data;
    }

}
