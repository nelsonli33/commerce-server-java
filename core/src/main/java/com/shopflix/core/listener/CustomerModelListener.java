package com.shopflix.core.listener;

import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;

@Component(value = "customerModelListener")
public class CustomerModelListener
{
    private SessionService sessionService;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerModelListener.class);

    @PostUpdate
    void onPostUpdate(CustomerModel customerModel) {
        getSessionService().setAttribute("customer", customerModel);
        LOG.info("CustomerModelListener.onPostUpdate(): " + customerModel.getUid());
    }

    @PostLoad
    void afterLoad(CustomerModel customerModel) {
        getSessionService().setAttribute("customer", customerModel);
        LOG.info("CustomerModelListener.afterLoad(): " + customerModel.getUid());
    }

    public SessionService getSessionService()
    {
        return sessionService;
    }

    @Autowired
    public void setSessionService(SessionService sessionService)
    {
        this.sessionService = sessionService;
    }
}
