package com.common;

import com.admin.service.CategoryService;
import com.admin.service.ImageService;
import com.admin.service.ModelService;


public class Factory {
	public static CategoryService getCategoryService(){return (CategoryService) BeanFactory.getBean("categoryService");}
	public static ModelService getModelService(){return (ModelService) BeanFactory.getBean("modelService");}
	public static ImageService getImageService(){return (ImageService) BeanFactory.getBean("imageService");}
}
