// import java.time.LocalDate;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyString;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.web.reactive.function.client.WebClient;
// import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

// import com.clienttest.citi_client.entities.TransactionEntity;
// import com.clienttest.citi_client.services.TransactionClientService;

// import reactor.core.publisher.Mono;

// @ExtendWith(MockitoExtension.class)
// public class TransactionClientServiceTest {

//     @Mock
//     private WebClient.Builder webClientBuilder;

//     @Mock
//     private WebClient webClient;

//     @Mock
//     private WebClient.RequestBodyUriSpec requestBodyUriSpec;

//     @Mock
//     private WebClient.RequestBodySpec requestBodySpec;

//     @Mock
//     private WebClient.RequestHeadersSpec requestHeadersSpec;

//     @Mock
//     private WebClient.ResponseSpec responseSpec;

//     @InjectMocks
//     private TransactionClientService transactionClientService;

//     private TransactionEntity transactionEntity;

//     @BeforeEach
//     public void setUp() {
//         transactionEntity = new TransactionEntity();
//         transactionEntity.setAmount(100.0);
//         transactionEntity.setDate(LocalDate.now());
//         transactionEntity.setCalculatedTax(10.0);
//     }

//     @Test
//     public void testSendTransaction() {
//         // Simulando el flujo encadenado de WebClient
//         when(webClient.post()).thenReturn(requestBodyUriSpec);
//         when(requestBodyUriSpec.uri("/api/transactions")).thenReturn(requestBodySpec);
//         when(requestBodySpec.bodyValue(any(TransactionEntity.class))).thenReturn(requestBodySpec);
//         when(requestBodySpec.retrieve()).thenReturn(responseSpec);
//         when(responseSpec.bodyToMono(TransactionEntity.class)).thenReturn(Mono.just(transactionEntity));

//         // Llamamos al método del servicio
//         transactionClientService.sendTransaction();

//         // Verificamos las interacciones con el mock de WebClient
//         verify(webClientBuilder, times(1)).baseUrl(anyString());
//         verify(webClientBuilder, times(1)).build();
//         verify(webClient, times(1)).post();
//         verify(requestBodyUriSpec, times(1)).uri("/api/transactions");
//         verify(requestBodySpec, times(1)).bodyValue(any(TransactionEntity.class));
//         verify(requestBodySpec, times(1)).retrieve();
//         verify(responseSpec, times(1)).bodyToMono(TransactionEntity.class);
//     }
// }
