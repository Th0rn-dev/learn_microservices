package se.magnus.microservices.core.review.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewService;
import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.util.http.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ReviewServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public List<Review> getReviews(int productId) {

        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        List<Review> lReviews = new ArrayList<>();
        if (productId == 213) {
            LOG.debug("No reviews found for productId: {}", productId);
            return lReviews;
        }
        lReviews.add(new Review(productId, 1, "Bob", "Subject 1", "!!!! AAAAAA", serviceUtil.getServiceAddress()));
        lReviews.add(new Review(productId, 2, "Mark", "Subject 2", "!!!! BBBBBB", serviceUtil.getServiceAddress()));
        lReviews.add(new Review(productId, 3, "Jon", "Subject 3", "!!!! CCCCCC", serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size: {}", lReviews.size());
        return lReviews;
    }
}
