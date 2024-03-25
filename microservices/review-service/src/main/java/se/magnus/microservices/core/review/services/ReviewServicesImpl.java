package se.magnus.microservices.core.review.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewService;
import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.util.http.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class ReviewServicesImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServicesImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ReviewServicesImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId) {

        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        List<Review> lReviews = new ArrayList<>();
        if (productId == 213) {
            LOG.debug("No reviews found for productId: {}", productId);
            return lReviews;
        }
        LOG.debug("");
        lReviews.add(new Review(productId, 1, "Bob", "Subject 1", "!!!! AAAAAA", serviceUtil.getServiceAddress()));
        lReviews.add(new Review(productId, 2, "Mark", "Subject 2", "!!!! BBBBBB", serviceUtil.getServiceAddress()));
        lReviews.add(new Review(productId, 3, "Jon", "Subject 3", "!!!! CCCCCC", serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size: {}", lReviews.size());
        return lReviews;
    }
}
