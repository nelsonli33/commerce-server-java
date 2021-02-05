package com.shopflix.merchant.data;

import org.springframework.data.domain.Sort;

public class ProductSearchCriteria
{
    private Integer page;
    private Integer limit;
    private Sort.Direction direction;
    private String sort;
    private String status;

    public ProductSearchCriteria(Builder builder)
    {
        this.page = builder.page;
        this.limit = builder.limit;
        this.direction = builder.direction;
        this.sort = builder.sort;
        this.status = builder.status;
    }

    public Integer getPage()
    {
        return page;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public Sort.Direction getDirection()
    {
        return direction;
    }

    public String getSort()
    {
        return sort;
    }

    public String getStatus()
    {
        return status;
    }

    /**
     * The builder class.
     */
    public static class Builder {

        private Integer page;
        private Integer limit;
        private Sort.Direction direction;
        private String sort;
        private String status;


        public Builder setPage(Integer page) {
            this.page = page;
            return this;
        }

        public Builder setLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder setDirection(Sort.Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder setSort(String sort) {
            this.sort = sort;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ProductSearchCriteria build() {
            return new ProductSearchCriteria(this);
        }
    }

}
