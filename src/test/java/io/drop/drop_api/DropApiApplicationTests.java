package io.drop.drop_api;

import io.drop.drop_api.models.entities.product.Products;
import io.drop.drop_api.models.entities.product.Skus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class DropApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateProductWithSkus() {
		// Criar um produto em memória



		Products product = Products.builder()
				.name("Tênis Adidas")
				.description("Tênis para corrida")
				.build();

		// Criar dois SKUs associados ao produto

		Skus skus1 = new Skus();
		 skus1 = Skus.builder()
				.color("Azul")
				.size("42")
				.price(new BigDecimal("150.00"))
				.stock(20)
				.codeSku(skus1.generateCodeSku())
				.is_promotion(false)
				.products(product)  // Associar ao produto
				.build();

		Skus skus2 = new Skus();
		skus2 = Skus.builder()
				.color("Vermelho")
				.size("43")
				.price(new BigDecimal("160.00"))
				.stock(15)
				.codeSku(skus2.generateCodeSku())
				.is_promotion(true)
				.price_promotion(new BigDecimal("140.00"))
				.products(product)  // Associar ao produto
				.build();

		// Adicionar os SKUs ao produto
		product.setSkus(Arrays.asList(skus1, skus2));


		// Verificações
		assertNotNull(product);
		assertEquals("Tênis Adidas", product.getName());
		assertEquals(2, product.getSkus().size());

		// Verificar se os SKUs foram corretamente associados ao produto
		Skus firstSku = product.getSkus().get(0);
		assertEquals("Azul", firstSku.getColor());
		assertEquals("42", firstSku.getSize());
		assertEquals(new BigDecimal("150.00"), firstSku.getPrice());

		Skus secondSku = product.getSkus().get(1);
		assertEquals("Vermelho", secondSku.getColor());
		assertEquals("43", secondSku.getSize());
		assertEquals(new BigDecimal("160.00"), secondSku.getPrice());
		assertEquals(new BigDecimal("140.00"), secondSku.getPrice_promotion());
		assertEquals(true, secondSku.is_promotion());

		// Exibir informações do produto no console
		System.out.println("Produto: " + product.getName());
		System.out.println("Descrição: " + product.getDescription());
		System.out.println("Quantidade de SKUs: " + product.getSkus().size());

		// Verificar e exibir os detalhes dos SKUs
		for (Skus sku : product.getSkus()) {
			System.out.println("----- SKU Detalhes -----");
			System.out.println("Código SKU: " + sku.getCodeSku());
			System.out.println("Cor: " + sku.getColor());
			System.out.println("Tamanho: " + sku.getSize());
			System.out.println("Preço: " + sku.getPrice());
			System.out.println("Em promoção: " + (sku.is_promotion() ? "Sim" : "Não"));
			if (sku.is_promotion()) {
				System.out.println("Preço promocional: " + sku.getPrice_promotion());
			}
			System.out.println("Estoque: " + sku.getStock());
			System.out.println();
		}
	}

}
