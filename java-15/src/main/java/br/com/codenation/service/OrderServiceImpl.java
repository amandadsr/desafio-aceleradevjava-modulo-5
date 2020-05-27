package br.com.codenation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		
		Double valorComDesconto = items.stream()
				.filter(item -> productRepository.findById(item.getProductId()).get().getIsSale() == true)
				.mapToDouble(item -> productRepository.findById(item.getProductId()).get().getValue() * item.getQuantity())
				.sum();
		
		valorComDesconto = valorComDesconto * 0.8;
		
		Double valorSemDesconto = items.stream()
				.filter(item -> productRepository.findById(item.getProductId()).get().getIsSale() == false)
				.mapToDouble(item -> productRepository.findById(item.getProductId()).get().getValue() * item.getQuantity())
				.sum();
		
		return valorComDesconto + valorSemDesconto;
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		
		return ids.stream()
				.filter(id -> productRepository.findById(id).isPresent())
				.map(id -> productRepository.findById(id).get())
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		
		return orders.stream()
				.mapToDouble(order -> calculateOrderValue(order))
				.sum();
		
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		
		List<Product> listProdutoTrue = productIds.stream()
				.filter(produto -> productRepository.findById(produto).get().getIsSale() == true)
				.map(produto -> productRepository.findById(produto).get())
				.collect(Collectors.toList());
		
		List<Product> listProdutoFalse = productIds.stream()
				.filter(produto -> productRepository.findById(produto).get().getIsSale() == false)
				.map(produto -> productRepository.findById(produto).get())
				.collect(Collectors.toList());
		
		Map<Boolean, List<Product>> mapListProduto = new HashMap<>();
		
		mapListProduto.put(true, listProdutoTrue);
		mapListProduto.put(false, listProdutoFalse);
		
		return mapListProduto;
	}

}