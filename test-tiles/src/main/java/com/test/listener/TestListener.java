package com.test.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.ApplicationResource;
import org.apache.tiles.startup.AbstractTilesInitializer;
import org.apache.tiles.startup.TilesInitializer;
import org.apache.tiles.web.startup.simple.SimpleTilesListener;

public class TestListener extends SimpleTilesListener {

	@Override
	protected TilesInitializer createTilesInitializer() {
		
		return new AbstractTilesInitializer(){
			@Override
			protected AbstractTilesContainerFactory createContainerFactory(ApplicationContext context) {
				
				BasicTilesContainerFactory basicTilesContainerFactory = new BasicTilesContainerFactory(){
					@Override
					protected List<ApplicationResource> getSources(ApplicationContext applicationContext) {
						//applicationContext.getResources（“/ WEB-INF / my-tiles-definitions-file.xml”）;
						
						List<ApplicationResource> retValue = new ArrayList<ApplicationResource>(1);
						retValue.add(applicationContext.getResource("/tiles/test-tiles.xml"));//修改tiles文件位置
						return retValue;
						//return super.getSources(applicationContext);
					}
				};
				return basicTilesContainerFactory;
			}
		};
	}

}
